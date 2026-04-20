(defn check_char
  "	Write a function to check whether the given string starts and ends with the same character or not."
  [string]
  (let [s (str string)]
    (and (not (empty? s))
         (= (first s) (last s)))))