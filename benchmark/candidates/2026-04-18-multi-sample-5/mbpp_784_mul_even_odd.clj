(defn mul_even_odd
  "	Write a function to find the product of first even and odd number of a given list."
  [list1]
  (let [first-even (first (filter even? list1))
        first-odd  (first (filter odd? list1))]
    (when (and first-even first-odd)
      (* first-even first-odd))))