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
  (let [s (or txt "")]
    (cond
      (re-find #"\s" s)
      (vec (remove empty? (clojure.string/split s #"\s+")))

      (clojure.string/includes? s ",")
      (vec (clojure.string/split s #","))

      :else
      (count
       (filter
        (fn [ch]
          (let [code (- (int ch) (int \a))]
            (and (<= 0 code 25) (odd? code))))
        s)))))