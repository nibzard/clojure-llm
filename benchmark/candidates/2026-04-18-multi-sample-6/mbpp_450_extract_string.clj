(defn extract_string
  "	Write a function to extract specified size of strings from a given list of string values."
  [str l]
  (let [n (if (number? l) l (count l))
        coll (if (number? l) str l)]
    (->> coll
         (filter string?)
         (filter #(= (count %) n)))))