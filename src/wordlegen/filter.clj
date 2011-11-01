(ns wordlegen.filter)

(defn split-into-words
  "Helper function to split a tweet into a sequence of words"
  [tweet]
  (re-seq #"\S+" tweet))

(defn filter1
  "This function returns the length of a word"
  [word]
  0	;; replace by your implementation!
  )

(defn filter2
  "This function returns a list with the last words of a sequence of tweets"
  [tweet-texts]
  nil ;; replace by your implementation!
  )

(defn filter3
  "This function returns a map with hash tags"
  [tweet-text]
  {"nothing" 0} ;; replace by your own implementation!
  )

(defn filter4
  "This function returns a map with the user as key, #followers as value"
  [raw-tweets]
  {"Maurits" 271} ;; replace by your implementation!
  )
