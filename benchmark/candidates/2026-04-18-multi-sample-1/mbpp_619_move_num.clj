(defn move_num
  "	Write a function to move all the numbers to the end of the given string."
  [test_str]
  (let [s (or test_str "")
        non-digits (apply str (remove #(Character/isDigit ^char %) s))
        digits (apply str (filter #(Character/isDigit ^char %) s))]
    (str non-digits digits)))