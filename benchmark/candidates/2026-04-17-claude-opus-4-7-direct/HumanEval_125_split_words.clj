(defn split_words
  "Given a string of words, return a list of words split on whitespace, if no whitespaces exists in the text you
  should split on commas ',' if no commas exists you should return the number of lower-case letters with odd order in the
  alphabet, ord('a') = 0, ord('b') = 1, ... ord('z') = 25"
  [txt]
  (cond
    (clojure.string/includes? txt " ") (clojure.string/split txt #"\s+")
    (clojure.string/includes? txt ",") (clojure.string/split txt #",")
    :else (count (filter (fn [c] (odd? (- (int c) (int \a)))) txt))))