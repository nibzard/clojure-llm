(defn extract_string
  "	Write a function to extract specified size of strings from a given list of string values."
  [str l]
  (let [n (when (number? l) l)
        coll (cond
               (sequential? str) str
               (sequential? l) l
               :else nil)]
    (if (and n coll)
      (filter #(= (count %) n) coll)
      '())))