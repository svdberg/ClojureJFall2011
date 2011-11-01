(ns wordlegen.filter)

(defn split-into-words
  "Helper function to split a tweet into a sequence of words"
  [tweet]
  (re-seq #"\S+" tweet))

(defn filter1
  "This function returns the length of a word"
  [word]
  (count word)
  ;; 0	; replace by your implementation!
 )

(defn filter2
  "This function returns a list with the last words of a sequence of tweets"
  [tweet-texts]
  (map last tweet-texts)
  ;; nil ; replace by your implementation!
  )

(defn filter3
  "This function returns a map with hash tags"
  [tweet-text]
  (frequencies (filter #(re-matches #"^#.*" %) (split-into-words tweet-text))))

(defn filter4
  "This function returns a map with the user as key, #followers as value"
  [raw-tweets]

  (let [users (map :user raw-tweets)
        names (map :name users)
        followers (map :followers_count users)]
    (zipmap names followers))
    ; {"Maurits" 271} ; replace by your implementation!
  )
