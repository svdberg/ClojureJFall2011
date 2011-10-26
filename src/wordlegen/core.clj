(ns wordlegen.core
  (:use [wordlegen wordlegen twitter]))

(defn -main [& [args]]
  (let [tweets (take-n-twitter-firehose 10)]
    (println tweets)
    (create-wordle tweets)))
