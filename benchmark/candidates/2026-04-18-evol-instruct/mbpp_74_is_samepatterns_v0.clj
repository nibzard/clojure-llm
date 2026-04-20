(defn pattern-bijection?
  "Return true when the items in `a` and `b` form a one-to-one pattern match.

  The two collections must have the same length, and each distinct item in `a`
  must map to exactly one distinct item in `b`, and vice versa.

  Examples:
  (pattern-bijection? [:red :blue :red] [1 2 1]) => true
  (pattern-bijection? [:red :blue :red] [1 1 1]) => false
  (pattern-bijection? nil nil) => true"
  [a b]
  (let [a (or a [])
        b (or b [])
        pairs (map vector a b)]
    (and (= (count a) (count b))
         (= (count (set a)) (count (set (map first pairs))))
         (= (count (set b)) (count (set (map second pairs))))
         (= (count (set pairs)) (count (set a)) (count (set b))))))