package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by chi-wing on 4/30/14.
 */
public class SignerManipulationExampleTest {

    @Test
    public void verifyResult() {
        SignerManipulationExample signerManipulationExample = new SignerManipulationExample(Props.get());
        signerManipulationExample.run();
        DocumentPackage documentPackage = signerManipulationExample.getEslClient().getPackage(signerManipulationExample.getPackageId());

        assertThat("Not correct number of signers", documentPackage.getSigners().size(), is(3));

        assertThat("Signer was not added", documentPackage.getSigners().containsKey(signerManipulationExample.email3), is(true));
        assertThat("Added signer first name was not set correctly", documentPackage.getSigner(signerManipulationExample.email3).getFirstName(), is(equalTo("firstName3")));
        assertThat("Added signer last name was not set correctly", documentPackage.getSigner(signerManipulationExample.email3).getLastName(), is(equalTo("lastName3")));
        assertThat("Added signer title was not set correctly", documentPackage.getSigner(signerManipulationExample.email3).getTitle(), is(equalTo("Title3")));

        assertThat("Signer 1 was not removed", documentPackage.getSigners().containsKey(signerManipulationExample.email1), is(false));

        assertThat("Signer 2 email was not updated correctly", documentPackage.getSigners().containsKey("timbob@aol.com"), is(true));
        assertThat("Signer 2 first name was not updated correctly", documentPackage.getSigner("timbob@aol.com").getFirstName(), is(equalTo("updateFirstName1")));
        assertThat("Signer 2 last name was not updated correctly", documentPackage.getSigner("timbob@aol.com").getLastName(), is(equalTo("updateLastName1")));
        assertThat("Signer 2 title was updated set correctly", documentPackage.getSigner("timbob@aol.com").getTitle(),is(equalTo("updateTitle1")));
    }
}