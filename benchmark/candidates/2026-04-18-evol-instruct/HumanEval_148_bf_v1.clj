(defn bf2
  "Given a sequence of planet names, return a lazy sequence of the planets
  whose orbits lie strictly between the first and last planets in the input,
  ordered by proximity to the Sun.

  Planet names are case-sensitive and must be one of:
  \"Mercury\", \"Venus\", \"Earth\", \"Mars\", \"Jupiter\", \"Saturn\",
  \"Uranus\", \"Neptune\".

  If the input has fewer than 2 elements, or if either endpoint is invalid,
  return an empty sequence.

  Examples:
  (bf2 [\"Jupiter\" \"Neptune\"])
  => (\"Saturn\" \"Uranus\")

  (bf2 [\"Earth\" \"Mercury\"])
  => (\"Venus\")

  (bf2 [\"Mercury\" \"Uranus\"])
  => (\"Venus\" \"Earth\" \"Mars\" \"Jupiter\" \"Saturn\")

  (bf2 [\"Mars\"])
  => ()"
  [planets]
  (let [order ["Mercury" "Venus" "Earth" "Mars" "Jupiter" "Saturn" "Uranus" "Neptune"]
        a (first planets)
        b (last planets)
        ia (.indexOf order a)
        ib (.indexOf order b)]
    (if (or (< ia 0) (< ib 0) (< (count planets) 2))
      ()
      (let [lo (min ia ib)
            hi (max ia ib)]
        (drop (inc lo) (take hi order))))))