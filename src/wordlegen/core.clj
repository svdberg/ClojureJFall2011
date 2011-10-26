(ns wordlegen.core
  (:import (java.io File))
  (:import (sandbox MyWordle Word))
  (:use [wordlegen wordle twitter filter])
  (:use [clojure.data.json :only (json-str write-json read-json)]))

(defn- call [^String nm & args]
  (when-let [fun (ns-resolve *ns* (symbol (str "wordlegen.core/" nm)))]
    (apply fun args)))

(defn filter-tweet1 [json-tweet]
  (when-let [twit (:text (read-json json-tweet))]
    (map #(str (filter1 %)) (re-seq #"\w+" twit))))

(defn example1 []
  (let [tweets (take-n-twitter-firehose 10)
        word-list (map filter-tweet1 tweets)]
    (frequencies (flatten word-list))))

(defn -main [& args]
  (let [wordle (MyWordle.)
        function (first args)]
    (.setAllowRotate wordle true)
    (add-words wordle (call function))
    (.doLayout wordle)
    (.saveAsPNG wordle (File. "wordle.png"))))
               
