(ns wordlegen.twitter
  (:require [http.async.client :as c])
  (:use [clojure.data.json :only (json-str write-json read-json)])
  (:use [clojure.java.io]))

(def ^:dynamic *credentials-file-name* "credentials.txt")
(def ^:dynamic *filename* "resources/firehosedump.txt")

(defn- read-file-as-seq [filename]
  (line-seq (reader filename)))

(defn- take-n-tweets-from-file [n]
  (take n (read-file-as-seq *filename*)))

(defn- load-keyfile
  [filename]
  (let [key-map (try (slurp filename) (catch Exception _ nil))]
    (if (nil? key-map)
      nil
      (read-string key-map))))

(defn- twitter-credentials
  "loads twitter username + password from credentials.txt"
  []
  (load-keyfile *credentials-file-name*))

(defn- take-n [seq n]
  (cond
   (= n 0) nil
   (= n 1) (list (first (c/string seq)))
   :else (cons (first (c/string seq)) (take-n seq (dec n)))))

(defn- take-n-tweets-from-firehose [n]
  (with-open [client (c/create-client)]
    (let [u (:user (twitter-credentials))
          p (:password (twitter-credentials))
          resp (c/stream-seq client :get "https://stream.twitter.com/1/statuses/sample.json"
                           :auth {:user u :password p})]
      (take-n resp n))))

(defn- write-one-line [filename string]
  (with-open [wrtr (writer filename :append true)]
    (.write wrtr (str string "\n"))))

(defn- write-to-file [filename n]
  (doseq [line (take-n-tweets-from-firehose n)]
    (write-one-line filename line))) 

;;define the protocol for retrieving a seq of tweets in json-format
(defprotocol TweetSeq
  "Protocol for retrieving n tweets from the firehose, as a seq of json"
  (take-n-tweets [x n]))

(deftype OnlineFirehose [] TweetSeq
         (take-n-tweets [x n] (take-n-tweets-from-firehose n)))

(deftype OfflineFirehose [] TweetSeq
         (take-n-tweets [x n] (take-n-tweets-from-file n)))

(def twitter-impl (OfflineFirehose.))
