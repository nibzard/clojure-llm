(defn count_char_position
  "	Write a function to count the number of characters in a string that occur at the same position in the string as in the English alphabet (case insensitive)."
  [str1]
  (let [s (or str1 "")]
    (->> s
         (map-indexed (fn [idx ch]
                        (= (Character/toLowerCase ^char ch)
                           (char (+ (int \a) idx)))))
         (filter true?)
         count)))