(defn add_string
  "	Write a function to apply a given format string to all of the elements in a list."
  [list_ string]
  (map #(str % string) list_))