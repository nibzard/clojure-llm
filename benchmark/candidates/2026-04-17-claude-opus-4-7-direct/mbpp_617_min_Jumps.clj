(defn min_Jumps
  "Write a function to check for the number of jumps required of given length to reach a point of form (d, 0) from origin in a 2d plane."
  [steps d]
  (let [n (count steps)
        ;; Calculate the maximum distance reachable with n jumps.
        ;; The maximum distance is achieved by alternating directions (e.g., +, -, +, -...)
        ;; which results in the sum: steps[0] - steps[1] + steps[2] - ... + steps[n-1]
        ;; We can calculate this efficiently by sorting the steps.
        ;; The largest step must be positive, the next largest negative, and so on.
        sorted-steps (sort > steps)
        max-dist (apply + (map-indexed (fn [i x] (if (odd? i) (- x) x)) sorted-steps))]
    ;; If the target distance d is greater than the maximum possible distance,
    ;; it is impossible to reach (d, 0) with the given steps.
    (if (> d max-dist)
      -1
      ;; If reachable, the minimum number of jumps is always equal to the total number of steps.
      ;; This is because we can construct a path to any point (x, 0) where |x| <= max_dist
      ;; using all n jumps.
      n)))