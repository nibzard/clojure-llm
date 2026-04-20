(defn re_arrange_array
  "	Write a function that takes in a vector and an integer n, and re-arranges the first n elements of the given vector so that all negative elements appear before positive ones, and where the relative order among negative and positive elements is preserved."
  [arr n]
  (let [v (vec (or arr []))
        cnt (count v)
        k (max 0 (min (or n 0) cnt))
        prefix (subvec v 0 k)
        suffix (subvec v k)
        negatives (filterv neg? prefix)
        non-negatives (filterv (complement neg?) prefix)]
    (into [] (concat negatives non-negatives suffix))))