(defn problem-interpose-a-seq [a b c]
  (if (empty? b)
    []
    (reduce (fn [acc x] (conj acc a x)) [(first b)] (rest b))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-interpose-a-seq
  (is (= (= (problem-interpose-a-seq 0 [1 2 3]) [1 0 2 0 3])))
  (is (= (= (apply str (problem-interpose-a-seq ", " ["one" "two" "three"])) "one, two, three")))
  (is (= (= (problem-interpose-a-seq :z [:a :b :c :d]) [:a :z :b :z :c :z :d]))))

(run-tests)
