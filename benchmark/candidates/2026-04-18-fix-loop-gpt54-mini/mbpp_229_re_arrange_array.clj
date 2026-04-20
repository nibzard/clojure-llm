(defn re_arrange_array
  "	Write a function that takes in a vector and an integer n, and re-arranges the first n elements of the given vector so that all negative elements appear before positive ones, and where the relative order among negative and positive elements is preserved."
  [arr n]
  (let [prefix (take n arr)
        negatives (filter neg? prefix)
        non-negatives (remove neg? prefix)
        rearranged-prefix (concat negatives non-negatives)]
    (vec (concat rearranged-prefix (drop n arr)))))