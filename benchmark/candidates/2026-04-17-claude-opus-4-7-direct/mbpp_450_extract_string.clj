(defn extract_string
  "Write a function to extract specified size of strings from a given list of string values."
  [str l]
  (filter #(= (count %) l) str))