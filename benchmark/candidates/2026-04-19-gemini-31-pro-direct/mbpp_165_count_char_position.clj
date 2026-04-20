(defn count_char_position
  "Write a function to count the number of characters in a string that occur at the same position in the string as in the English alphabet (case insensitive)."
  [str1]
  (if (empty? str1)
    0
    (count
     (keep-indexed
      (fn [idx ch]
        (when (= idx (- (int ch) (int \a)))
          ch))
      (clojure.string/lower-case str1)))))