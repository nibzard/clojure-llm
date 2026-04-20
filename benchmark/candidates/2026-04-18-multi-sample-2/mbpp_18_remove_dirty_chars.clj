(defn remove_dirty_chars
  "	Write a function to remove characters from the first string which are present in the second string."
  [string second_string]
  (let [s1 (or string "")
        dirty? (set (or second_string ""))]
    (apply str (remove dirty? s1))))