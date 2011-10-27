(ns wordlegen.test.filter
  (:use [wordlegen.filter])
  (:use [clojure.test]))

(deftest test-filter1
  (is (=  0 ( filter1 "")))
  (is (= 3  ( filter1 "thr")))
  (is (= 19 ( filter1 "thisisaverylongword"))))

(deftest test-filter2
  "Do we want the last word of a tweet?"
  (let [example-tweet "this is an example tweet text"
        example-tweet-last-words "is an example tweet text"
        result-list '(example-tweet-last-words example-tweet-last-words)]
    (is (= result-list (filter2 [example-tweet, example-tweet])))
    (is (= [] (filter2 [])))))

(deftest test-filter3
  )
