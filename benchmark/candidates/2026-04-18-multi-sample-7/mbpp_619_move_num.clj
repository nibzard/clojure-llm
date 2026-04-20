(defn move_num
  "	Write a function to move all the numbers to the end of the given string."
  [test_str]
  (let [s (or test_str "")]
    (apply str
           (concat (remove #(Character/isDigit ^char %) s)
                   (filter #(Character/isDigit ^char %) s)))))