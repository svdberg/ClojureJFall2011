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
  (let [tweets (take-n-twitter-firehose *tweets*)]
    (remove nil? (map get-text tweets))))

(defn filter-tweet1 [tweet]
  (map #(str (filter1 %)) (split-into-words tweet)))

(defn example1 []
  (let [word-list (map filter-tweet1 (get-tweets))]
    (frequencies (flatten word-list))))

(defn -main [& args]
  (let [wordle (MyWordle.)
        function (first args)]
    (.setAllowRotate wordle true)
    (add-words wordle (call function))
    (.doLayout wordle)
    (.saveAsPNG wordle (File. "wordle.png"))))
               
