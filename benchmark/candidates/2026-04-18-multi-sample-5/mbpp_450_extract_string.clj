(defn extract_string
  "	Write a function to extract specified size of strings from a given list of string values."
  [str l]
  (let [n (if (number? l) l (count (or l [])))
        s (or str [])]
    (->> s
         (filter string?)
         (filter #(= (count %) n))
         vec)))