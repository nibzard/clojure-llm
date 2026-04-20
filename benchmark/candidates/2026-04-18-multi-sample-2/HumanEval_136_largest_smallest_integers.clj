(defn largest_smallest_integers
  "Create a function that returns a vector (a, b), where 'a' is
  the largest of negative integers, and 'b' is the smallest
  of positive integers in a list.
  If there is no negative or positive integers, return them as nil.
  Examples:
  >>> (largest_smallest_integers [2 4 1 3 5 7])
  [nil 1]
  >>> (largest_smallest_integers [])
  [nil nil]
  >>> (largest_smallest_integers [0])
  [nil nil]"
  [lst]
  (reduce (fn [[largest-neg smallest-pos] x]
            [(if (neg? x)
               (if (or (nil? largest-neg) (> x largest-neg)) x largest-neg)
               largest-neg)
             (if (pos? x)
               (if (or (nil? smallest-pos) (< x smallest-pos)) x smallest-pos)
               smallest-pos)])
          [nil nil]
          lst))