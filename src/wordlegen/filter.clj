(ns wordlegen.filter)

(defn filter1
  "This function returns the length of a word"
  [word]
  (count word)
  ;; 0	; replace by your implementation!
 )

(defn filter2
  "This function returns a list with the last words of a list of tweets"
  [tweets]
  (map last tweets)
  ;; nil ; replace by your implementation!
  )

(defn filter5
  "This function returns a map with the user as key, #followers as value"
  [raw-tweets]

  (let [users (map :user raw-tweets)
        names (map :name users)
        followers (map :followers_count users)]
    (zipmap names followers))
    ; {"Maurits" 271} ; replace by your implementation!
  )
