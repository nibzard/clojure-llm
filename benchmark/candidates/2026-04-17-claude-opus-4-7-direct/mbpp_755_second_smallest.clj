(defn second_smallest
  "Write a function to find the second smallest number in a list."
  [numbers]
  (if (or (nil? numbers) (empty? numbers))
    nil
    (let [sorted (sort numbers)]
      (if (< (count sorted) 2)
        nil
        (second sorted)))))