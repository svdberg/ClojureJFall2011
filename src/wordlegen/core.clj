(ns wordlegen.core
  (:use [wordlegen wordlegen twitter]))

(defn- call [^String nm]
  (when-let [fun (ns-resolve *ns* (symbol nm))]
    (apply fun)))

(defn example1 []
  (println "example1!"))

(defn -main [& args]
  (let [tweets (take-n-twitter-firehose 10)]
    (call (first args))))
;    (println tweets)
;    (create-wordle tweets)))
