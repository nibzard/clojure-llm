(defn remove_dirty_chars
  "	Write a function to remove characters from the first string which are present in the second string."
  [string second_string]
  (let [dirty-set (set second_string)]
    (apply str (remove dirty-set string))))