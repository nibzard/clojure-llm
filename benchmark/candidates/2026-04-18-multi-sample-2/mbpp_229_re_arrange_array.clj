(defn re_arrange_array
  "	Write a function that takes in a vector and an integer n, and re-arranges the first n elements of the given vector so that all negative elements appear before positive ones, and where the relative order among negative and positive elements is preserved."
  [arr n]
  (let [arr (vec (or arr []))
        cnt (count arr)
        n (max 0 (min (or n 0) cnt))
        prefix (subvec arr 0 n)
        suffix (subvec arr n)
        negatives (filterv neg? prefix)
        non-negatives (filterv (complement neg?) prefix)]
    (into [] (concat negatives non-negatives suffix))))