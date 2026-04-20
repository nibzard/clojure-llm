(defn check_char
  "	Write a function to check whether the given string starts and ends with the same character or not."
  [string]
  (let [s (str string)]
    (if (empty? s)
      false
      (if (= (first s) (last s)) "Valid" "Invalid"))))