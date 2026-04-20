(defn diff_even_odd
  "	Write a function to find the difference of the first even and first odd number of a given list."
  [list1]
  (let [first-even (first (filter even? list1))
        first-odd  (first (filter odd? list1))]
    (when (and (some? first-even) (some? first-odd))
      (- first-even first-odd))))