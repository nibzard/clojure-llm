(defn remove_length
  "Write a function to remove all the words with k length in the given string."
  [test_str K]
  (when test_str
    (clojure.string/join " "
                         (remove #(= K (count %))
                                 (clojure.string/split test_str #"\s+")))))