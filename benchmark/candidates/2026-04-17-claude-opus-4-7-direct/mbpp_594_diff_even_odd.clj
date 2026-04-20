(defn diff_even_odd
  "Write a function to find the difference of the first even and first odd number of a given list."
  [list1]
  (let [evens (filter even? list1)
        odds (filter odd? list1)
        first-even (first evens)
        first-odd (first odds)]
    (if (and first-even first-odd)
      (- first-even first-odd)
      nil)))