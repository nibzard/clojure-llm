(defn problem-map-construction [a b]
  (into {} (map vector a b)))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-map-construction
  (is (= (= (problem-map-construction [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3})))
  (is (= (= (problem-map-construction [1 2 3 4] ["one" "two" "three"]) {1 "one", 2 "two", 3 "three"})))
  (is (= (= (problem-map-construction [:foo :bar] ["foo" "bar" "baz"]) {:foo "foo", :bar "bar"}))))

(run-tests)
