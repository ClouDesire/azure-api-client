package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlType (name = "SSH")
@XmlAccessorType (value = XmlAccessType.FIELD)
public class SshKeyContainer
{
	@XmlElementWrapper (name = "PublicKeys")
	@XmlElement (name = "PublickKey", type = PublicKey.class)
	private List<PublicKey> publicKeys = new ArrayList<>();

	@XmlElementWrapper (name = "KeyPairs")
	@XmlElement (name = "KeyPair", type = KeyPair.class, nillable = true)
	private List<KeyPair> keyPairs;

	public List<PublicKey> getPublicKeys()
	{
		return publicKeys;
	}

	public void setPublicKeys( List<PublicKey> publicKeys )
	{
		this.publicKeys = publicKeys;
	}

	public List<KeyPair> getKeyPairs()
	{
		return keyPairs;
	}

	public void setKeyPairs( List<KeyPair> keyPairs )
	{
		this.keyPairs = keyPairs;
	}
}
