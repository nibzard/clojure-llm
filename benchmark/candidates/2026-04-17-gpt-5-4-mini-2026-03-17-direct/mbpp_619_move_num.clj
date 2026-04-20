(defn move_num
  "	Write a function to move all the numbers to the end of the given string."
  [test_str]
  (let [s (or test_str "")
        chars (seq s)
        letters (apply str (remove #(Character/isDigit ^char %) chars))
        digits (apply str (filter #(Character/isDigit ^char %) chars))]
    (str letters digits)))