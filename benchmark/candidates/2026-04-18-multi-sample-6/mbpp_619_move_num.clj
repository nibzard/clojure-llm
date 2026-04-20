(defn move_num
  "	Write a function to move all the numbers to the end of the given string."
  [test_str]
  (let [s (or test_str "")
        chars (seq s)
        non-digits (remove #(Character/isDigit ^char %) chars)
        digits (filter #(Character/isDigit ^char %) chars)]
    (apply str (concat non-digits digits))))