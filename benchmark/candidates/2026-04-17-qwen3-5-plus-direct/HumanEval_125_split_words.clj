(defn split_words
  "Given a string of words, return a list of words split on whitespace, if no whitespaces exists in the text you
  should split on commas ',' if no commas exists you should return the number of lower-case letters with odd order in the
  alphabet, ord('a') = 0, ord('b') = 1, ... ord('z') = 25
  Examples
  >>> (split_words "Hello world!")
  ["Hello" "world!"]
  >>> (split_words "Hello,world!")
  ["Hello" "world!"]
  >>> (split_words "abcdef")
  3"
  [txt]
  (cond
    (re-find #"\s" txt)
    (clojure.string/split txt #"\s+")
    
    (re-find #"," txt)
    (clojure.string/split txt #",")
    
    :else
    (->> txt
         (filter #(Character/isLowerCase %))
         (map #(- (int %) (int \a)))
         (filter odd?)
         count)))