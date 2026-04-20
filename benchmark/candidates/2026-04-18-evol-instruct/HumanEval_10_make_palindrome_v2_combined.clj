(defn make_palindrome_vec
  "Return the shortest palindrome vector that begins with the supplied vector.

  The input is a vector of any values, not a string. Preserve element equality
  and return a vector.

  Examples:
  >>> (make_palindrome_vec [])
  []
  >>> (make_palindrome_vec [:a :b :c])
  [:a :b :c :a]
  >>> (make_palindrome_vec [1 2 1])
  [1 2 1]
  >>> (make_palindrome_vec [1 2 3 4])
  [1 2 3 4 3 2 1]"
  [v]
  (letfn [(pal? [xs] (= xs (vec (reverse xs))))]
    (if (empty? v)
      []
      (let [n (count v)]
        (loop [i 0]
          (let [suffix (subvec v i n)]
            (if (pal? suffix)
              (vec (concat v (reverse (subvec v 0 i))))
              (recur (inc i)))))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [] (make_palindrome_vec [])))
  (is (= [:a :b :c :a] (make_palindrome_vec [:a :b :c])))
  (is (= [1 2 1] (make_palindrome_vec [1 2 1])))
  (is (= [1 2 3 4 3 2 1] (make_palindrome_vec [1 2 3 4]))))

(run-test test-variation)
