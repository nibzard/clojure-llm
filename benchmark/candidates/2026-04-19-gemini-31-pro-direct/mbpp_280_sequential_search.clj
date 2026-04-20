(defn sequential_search
  "Write a function that takes in a vector and element and returns a vector containing a boolean that indicates if the element is in the vector and the index position of the element (or -1 if the element is not found)."
  [dlist item]
  (let [idx (first (keep-indexed #(when (= %2 item) %1) dlist))]
    (if (some? idx)
      [true idx]
      [false -1])))