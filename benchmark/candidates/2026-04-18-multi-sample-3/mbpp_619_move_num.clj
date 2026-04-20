(defn move_num
  "	Write a function to move all the numbers to the end of the given string."
  [test_str]
  (let [s (or test_str "")
        non-digits (remove #(Character/isDigit ^char %) s)
        digits (filter #(Character/isDigit ^char %) s)]
    (apply str (concat non-digits digits))))