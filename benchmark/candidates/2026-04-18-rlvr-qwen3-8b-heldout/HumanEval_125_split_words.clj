(defn split_words
  "Given a string of words, return a list of words split on whitespace, if no whitespaces exists in the text you
  should split on commas ',' if no commas exists you should return the number of lower-case letters with odd order in the
  alphabet, ord('a') = 0, ord('b') = 1, ... ord('z') = 25
  Examples
  >>> (split_words \"Hello world!\")
  [\"Hello\" \"world!\"]
  >>> (split_words \"Hello,world!\")
  [\"Hello\" \"world!\"]
  >>> (split_words \"abcdef\")
  3"
  [txt]
  (cond
    (nil? txt) []
    (re-find #"\s" txt) (re-seq #"\S+" txt)
    (re-find #"," txt) (re-seq #"[^,]+" txt)
    :else
    (count
      (filter #(odd? (- (int %) (int \a)))
              (apply str txt)))))