(ns wordlegen.core
  (:import (java.io File))
  (:import (sandbox MyWordle Word))
  (:use [wordlegen wordle twitter filter])
  (:use [clojure.data.json :only (json-str write-json read-json)]))

(def *tweets* 10)

(defn- call [^String nm & args]
  (when-let [fun (ns-resolve *ns* (symbol (str "wordlegen.core/" nm)))]
    (apply fun args)))

(defn split-into-words [tweet]
  (re-seq #"\w+" tweet))

(defn- get-text [tweet]
  (:text (read-json tweet)))

(defn- get-tweets []
  (let [tweets (take-n-tweets twitter-impl *tweets*)]
    (remove nil? (map get-text tweets))))

(defn- filter-tweet1 [tweet]
  (map #(str (filter1 %)) (split-into-words tweet)))

(defn example1
  "Puts word lenght in a Worldle"
  []
  (let [word-list (map filter-tweet1 (get-tweets))]
    (frequencies (flatten word-list))))

(defn example2
  "Puts all last words of a series of tweets in a Wordle"
  []
  (frequencies (filter2 (map split-into-words (get-tweets)))))

(defn example5
  "Put influence strength (nr of followers) in a Wordle"
  []
  (filter5 (map read-json (take-n-tweets twitter-impl *tweets*))))

(defn -main [& args]
  (let [wordle (MyWordle.)
        function (first args)]
    (.setAllowRotate wordle true)
    (.setOutputWidth wordle (Integer. 512))
    (add-words wordle (call function))
    (.doLayout wordle)
    (.saveAsPNG wordle (File. "wordle.png"))))
               
