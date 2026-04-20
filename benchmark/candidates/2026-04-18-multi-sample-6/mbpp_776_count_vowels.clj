(defn count_vowels
  "	Write a function to count those characters which have vowels as their neighbors in the given string."
  [test_str]
  (let [s (or test_str "")
        vowels? (set "aeiouAEIOU")
        chars (vec s)
        n (count chars)]
    (if (< n 3)
      0
      (count
       (for [i (range 1 (dec n))
             :when (and (vowels? (nth chars (dec i)))
                        (vowels? (nth chars (inc i))))]
         (nth chars i))))))