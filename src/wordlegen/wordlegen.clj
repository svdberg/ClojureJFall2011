(ns wordlegen.wordlegen
  (:use [wordlegen twitter wordle])
  (:import (sandbox MyWordle Word)
           (java.io File)))
           
(defn create-wordle [tweets]
  (let [wordle (MyWordle.)]
    (.setAllowRotate wordle true)
    (add-words wordle {"Clojure" 5 "Scala" 1 "Java" 2 "C" 1 "Fortran" 2})
    (.doLayout wordle)
    (.saveAsPNG wordle (File. "wordle.png"))))

