(defn count_char_position
  "Write a function to count the number of characters in a string that occur at the same position in the string as in the English alphabet (case insensitive)."
  [str1]
  (when (string? str1)
    (->> str1
         (map-indexed (fn [idx c]
                        (let [lower-c (Character/toLowerCase c)]
                          (when (and (>= lower-c \a) (<= lower-c \z))
                            (when (= (- (int lower-c) (int \a)) idx)
                              1)))))
         (remove nil?)
         (count))))