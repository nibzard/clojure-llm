(defn re_arrange_array
  "Write a function that takes in a vector and an integer n, and re-arranges the first n elements of the given vector so that all negative elements appear before positive ones, and where the relative order among negative and positive elements is preserved."
  [arr n]
  (let [first-n (take n arr)
        rest-arr (drop n arr)]
    (vec (concat (filter neg? first-n)
                 (remove neg? first-n)
                 rest-arr))))