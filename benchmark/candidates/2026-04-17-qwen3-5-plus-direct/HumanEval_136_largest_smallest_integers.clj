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
  (let [negatives (filter neg? lst)
        positives (filter pos? lst)
        largest-neg (when (seq negatives) (apply max negatives))
        smallest-pos (when (seq positives) (apply min positives))]
    [largest-neg smallest-pos]))