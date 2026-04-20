(defn bf
  "There are eight planets in our solar system: Mercury, Venus, Earth, Mars,
  Jupiter, Saturn, Uranus, and Neptune.

  Write a function that takes two planet names as strings, planet1 and planet2.
  Return a vector containing all planets whose names come alphabetically between
  planet1 and planet2, excluding the endpoints, sorted alphabetically.

  If either input is not a valid planet name, return an empty vector.

  Examples:
  >>> (bf \"Earth\" \"Neptune\")
  [\"Jupiter\" \"Mars\" \"Mercury\" \"Saturn\" \"Uranus\" \"Venus\"]
  >>> (bf \"Mars\" \"Mercury\")
  []
  >>> (bf \"Venus\" \"Earth\")
  [\"Jupiter\" \"Mars\" \"Mercury\" \"Neptune\" \"Saturn\" \"Uranus\"]
  "
  [planet1 planet2]
  (let [planets #{"Mercury" "Venus" "Earth" "Mars" "Jupiter" "Saturn" "Uranus" "Neptune"}
        ordered (sort planets)]
    (if (and (contains? planets planet1) (contains? planets planet2))
      (->> ordered
           (drop-while #(not= % planet1))
           rest
           (take-while #(not= % planet2))
           vec)
      [])))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["Jupiter" "Mars" "Mercury" "Saturn" "Uranus" "Venus"]
         (bf "Earth" "Neptune"))))

(run-test test-variation)
