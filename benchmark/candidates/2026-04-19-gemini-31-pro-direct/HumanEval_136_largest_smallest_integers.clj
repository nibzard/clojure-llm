(defn largest_smallest_integers
  "Create a function that returns a vector (a, b), where 'a' is
  the largest of negative integers, and 'b' is the smallest
  of positive integers in a list.
  If there is no negative or positive integers, return them as nil."
  [lst]
  (let [negs (filter neg? lst)
        poss (filter pos? lst)
        max-neg (when (seq negs) (apply max negs))
        min-pos (when (seq poss) (apply min poss))]
    [max-neg min-pos]))