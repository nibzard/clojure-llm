(defn move_num
  "Write a function to move all the numbers to the end of the given string."
  [test_str]
  (apply str (concat (remove #(Character/isDigit %) test_str)
                     (filter #(Character/isDigit %) test_str))))