(defproject wordlegen "1.0.0-SNAPSHOT"
  :description "Wordle cloud generator for JFall 2011"
  :main wordlegen.core
  :run-aliases {:wordle [wordlegen.core -main "arg1"]}
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojure/data.json "0.1.1"]
                 [http.async.client "0.3.1"]])
