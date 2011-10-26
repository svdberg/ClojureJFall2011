(ns wordlegen.wordle
  (:import (java.awt Color)
           (java.util Random))
  (:import (sandbox MyWordle Word)))

(def random (Random.))

(defn random-bool []
  (.nextBoolean random))

(defn random-int [max]
  (.nextInt random max))

(defn random-color []
  (Color. (random-int 100) (random-int 100) (random-int 100)))

(defn create-word [text weight]
  (doto (Word. text weight)
    (.setFill (random-color))
    (.setStroke (random-color))
    (.setFontFamily (if (random-bool) "Helvetica" "Courier"))))

(defn add [wordle text weight]
  (.add wordle (create-word text weight)))

(defn add-words [wordle words]
  (doseq [[text weight] words] (add wordle text weight)))
          